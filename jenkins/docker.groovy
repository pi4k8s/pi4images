node {
    stage('git chekout') {
        git branch: "master", url: 'https://gitee.com/pi4k8s/pi4images.git'
    }
    stage('dockerFile') {
        dir('jenkins'){
            stash 'jenkins'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push jenkins': {
                    sh 'cd jenkins && docker build . -t pi4k8s/jenkins-arm64:openjdk8-2.327'
                    sh 'docker push pi4k8s/jenkins-arm64:openjdk8-2.327'
                },
                'docker build && push openjdk amd64': {
                    node('amd64') {
                        dir('jenkins'){
                            unstash 'jenkins'
                        }
                        unstash 'jenkins'
                        sh 'cd jenkins && docker build . -t pi4k8s/jenkins-amd64:openjdk8-2.327'
                        sh 'docker push pi4k8s/jenkins-amd64:openjdk8-2.327'
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh 'docker manifest rm pi4k8s/jenkins:openjdk8-2.327'
        }catch(exc){
            echo "some thing wrong"
        }
        sh 'docker manifest create pi4k8s/jenkins:openjdk8-2.327 pi4k8s/jenkins-amd64:openjdk8-2.327 pi4k8s/jenkins-arm64:openjdk8-2.327'
        sh 'docker manifest annotate pi4k8s/jenkins:openjdk8-2.327 pi4k8s/jenkins-amd64:openjdk8-2.327 --os linux --arch amd64'
        sh 'docker manifest annotate pi4k8s/jenkins:openjdk8-2.327 pi4k8s/jenkins-arm64:openjdk8-2.327 --os linux --arch arm64'
        sh 'docker manifest push pi4k8s/jenkins:openjdk8-2.327'
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