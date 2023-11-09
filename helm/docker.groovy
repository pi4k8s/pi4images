node {
    parameters {
        string(
                description: '你需要用哪个版本进行构建 ?',
                name: 'helm_version',
                defaultValue: 'v3.9.0'
        )
    }
    stage('git chekout') {
        git branch: "master", url: 'https://gitee.com/pi4k8s/pi4images.git'
    }
    stage('dockerFile') {
        dir('helm'){
            stash 'helm'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push helm': {
                    echo "${params.helm_version}"
                    sh "cd helm && docker build . -t pi4k8s/helm-arm64:${params.helm_version} --build-arg HELM_VERSION=${params.helm_version} --build-arg ARCH=arm64"
                    sh "docker push pi4k8s/helm-arm64:${params.helm_version}"
                },
                'docker build && push helm amd64': {
                    node('amd64') {
                        dir('helm'){
                            unstash 'helm'
                        }
                        unstash 'helm'
                        echo "${params.helm_version}"
                        sh "cd helm && docker build . -t pi4k8s/helm-amd64:${params.helm_version} --build-arg HELM_VERSION=${params.helm_version} --build-arg ARCH=amd64"
                        sh "docker push pi4k8s/helm-amd64:${params.helm_version}"
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh "docker manifest rm pi4k8s/helm:${params.helm_version}"
        }catch(exc){
            echo "some thing wrong"
        }
        sh "docker manifest create pi4k8s/helm:${params.helm_version} pi4k8s/helm-amd64:${params.helm_version} pi4k8s/helm-arm64:${params.helm_version}"
        sh "docker manifest annotate pi4k8s/helm:${params.helm_version} pi4k8s/helm-amd64:${params.helm_version} --os linux --arch amd64"
        sh "docker manifest annotate pi4k8s/helm:${params.helm_version} pi4k8s/helm-arm64:${params.helm_version} --os linux --arch arm64"
        sh "docker manifest push pi4k8s/helm:${params.helm_version}"
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