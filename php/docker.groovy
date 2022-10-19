node {
    stage('git chekout') {
        git branch: "master", url: 'https://gitee.com/pi4k8s/pi4images.git'
    }
    stage('dockerFile') {
        dir('php'){
            stash 'php'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push ansible': {
                    sh "cd php && docker build . -t pi4k8s/php-amd64:7.4-apache"
                    sh "docker push pi4k8s/php-amd64:7.4-apache"
                },
                'docker build && push ansible arm64': {
                    node('arm64') {
                        dir('php'){
                            unstash 'php'
                        }
                        sh "cd php && docker build . -t pi4k8s/php-arm64:7.4-apache"
                        sh "docker push pi4k8s/php-arm64:7.4-apache"
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh "docker manifest rm pi4k8s/php:7.4-apache"
        }catch(exc){
            echo "some thing wrong"
        }
        sh "docker manifest create pi4k8s/php:7.4-apache pi4k8s/php-amd64:7.4-apache pi4k8s/php-arm64:7.4-apache"
        sh "docker manifest annotate pi4k8s/php:7.4-apache pi4k8s/php-amd64:7.4-apache --os linux --arch amd64"
        sh "docker manifest annotate pi4k8s/php:7.4-apache pi4k8s/php-arm64:7.4-apache --os linux --arch arm64"
        sh "docker manifest push pi4k8s/php:7.4-apache"
    }
}