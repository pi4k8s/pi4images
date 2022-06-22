node {
    parameters {
        choice(
                description: '你需要选择哪个版本进行构建 ?',
                name: 'ansible_version',
                choices: ['2.10.7', '3.4.0', '4.10.0']
        )
    }
    stage('git chekout') {
        git credentialsId: 'gogs11001', branch: "master", url: 'http://gogs.fastjrun.com:11001/devops/myDocker.git'
    }
    stage('dockerFile') {
        dir('common/ansible'){
            stash 'ansible'
        }
    }
    stage('parallel docker build') {
        parallel (
                'docker build && push ansible': {
                    echo "${params.ansible_version}"
                    sh "cd common/ansible && docker build . -t pi4k8s/ansible-centos7-arm64:${params.ansible_version} --build-arg ANSIBLE_VERSION=${params.ansible_version}"
                    sh "docker push pi4k8s/ansible-centos7-arm64:${params.ansible_version}"
                },
                'docker build && push ansible amd64': {
                    node('amd64') {
                        dir('common/ansible'){
                            unstash 'ansible'
                        }
                        unstash 'ansible'
                        echo "${params.ansible_version}"
                        sh "cd common/ansible && docker build . -t pi4k8s/ansible-centos7-amd64:${params.ansible_version} --build-arg ANSIBLE_VERSION=${params.ansible_version}"
                        sh "docker push pi4k8s/ansible-centos7-amd64:${params.ansible_version}"
                    }
                }
        )
    }
    stage('manifest'){
        try {
            sh "docker manifest rm pi4k8s/ansible-centos7:${params.ansible_version}"
        }catch(exc){
            echo "some thing wrong"
        }
        sh "docker manifest create pi4k8s/ansible-centos7:${params.ansible_version} pi4k8s/ansible-centos7-amd64:${params.ansible_version} pi4k8s/ansible-centos7-arm64:${params.ansible_version}"
        sh "docker manifest annotate pi4k8s/ansible-centos7:${params.ansible_version} pi4k8s/ansible-centos7-amd64:${params.ansible_version} --os linux --arch amd64"
        sh "docker manifest annotate pi4k8s/ansible-centos7:${params.ansible_version} pi4k8s/ansible-centos7-arm64:${params.ansible_version} --os linux --arch arm64"
        sh "docker manifest push pi4k8s/ansible-centos7:${params.ansible_version}"
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