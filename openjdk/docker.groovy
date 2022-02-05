node {
    stage('git chekout') {
        git branch: "master", url: 'https://gitee.com/fastjrun/pi4images.git'
    }
    stage('dockerFile') {
        dir('openjdk'){
            stash 'openjdk'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push openjdk': {
                    sh 'cd openjdk && docker build . -t pi4k8s/openjdk8-arm64:centos7'
                    sh 'docker push pi4k8s/openjdk8-arm64:centos7'
                },
                'docker build && push openjdk amd64': {
                    node('amd64') {
                        dir('openjdk'){
                            unstash 'openjdk'
                        }
                        unstash 'openjdk'
                        sh 'cd openjdk && docker build . -t pi4k8s/openjdk8-amd64:centos7'
                        sh 'docker push pi4k8s/openjdk8-amd64:centos7'
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh 'docker manifest rm pi4k8s/openjdk8:centos7'
        }catch(exc){
            echo "some thing wrong"
        }
        sh 'docker manifest create pi4k8s/openjdk8:centos7 pi4k8s/tomcat9-amd64:openjdk8 pi4k8s/tomcat9-arm64:openjdk8'
        sh 'docker manifest annotate pi4k8s/openjdk8:centos7 pi4k8s/tomcat9-amd64:openjdk8 --os linux --arch amd64'
        sh 'docker manifest annotate pi4k8s/openjdk8:centos7 pi4k8s/tomcat9-arm64:openjdk8 --os linux --arch arm64'
        sh 'docker manifest push pi4k8s/openjdk8:centos7'
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