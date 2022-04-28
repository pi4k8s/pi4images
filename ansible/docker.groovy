node {
    stage('git chekout') {
        git branch: "master", url: 'https://gitee.com/fastjrun/pi4images.git'
    }
    stage('dockerFile') {
        dir('ansible'){
            stash 'ansible'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push ansible': {
                    sh 'cd ansible && docker build . -t pi4k8s/ansible-arm64:centos7'
                    sh 'docker push pi4k8s/ansible-arm64:centos7'
                },
                'docker build && push ansible amd64': {
                    node('amd64') {
                        dir('ansible'){
                            unstash 'ansible'
                        }
                        unstash 'ansible'
                        sh 'cd ansible && docker build . -t pi4k8s/ansible-amd64:centos7'
                        sh 'docker push pi4k8s/ansible-amd64:centos7'
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh 'docker manifest rm pi4k8s/ansible:centos7'
        }catch(exc){
            echo "some thing wrong"
        }
        sh 'docker manifest create pi4k8s/ansible:centos7 pi4k8s/ansible-amd64:centos7 pi4k8s/ansible-arm64:centos7'
        sh 'docker manifest annotate pi4k8s/ansible:centos7 pi4k8s/ansible-amd64:centos7 --os linux --arch amd64'
        sh 'docker manifest annotate pi4k8s/ansible:centos7 pi4k8s/ansible-arm64:centos7 --os linux --arch arm64'
        sh 'docker manifest push pi4k8s/ansible:centos7'
    }
    stage('cleanWs'){
        parallel (
                'arm64': {
                    cleanWs()
                },
                'amd64': {
                    node('amd64') {
                        cleanWs()
                    }
                }
        )
    }
}