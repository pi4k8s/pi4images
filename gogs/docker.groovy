pipeline {
    agent any
    parameters {
        gitParameter name: 'TAG',
                type: 'PT_TAG',
                defaultValue: 'v0.12.3'
    }
    stages {
        stage('git chekout') {
            steps {
                checkout([$class                           : 'GitSCM',
                          branches                         : [[name: "${params.TAG}"]],
                          doGenerateSubmoduleConfigurations: false,
                          extensions                       : [],
                          gitTool                          : 'Default',
                          submoduleCfg                     : [],
                          userRemoteConfigs                : [[url: 'https://github.com/gogs/gogs.git']]
                ])
            }
        }
        stage('docker build & push') {
            steps {
                sh 'sed -i "7i\\ENV GOPROXY https://goproxy.cn,direct" docker/Dockerfile.aarch64'
                sh 'sed -i 14d docker/Dockerfile.aarch64'
                sh 'cp -f /var/software/gosu-arm64 gosu-arm64'
                sh 'sed -i "14i\\ADD gosu-arm64 /usr/sbin/gosu" docker/Dockerfile.aarch64'
                sh 'docker build . -t pi4k8s/gogs:v0.12.3 -f docker/Dockerfile.aarch64'
                sh 'docker push pi4k8s/gogs:v0.12.3'
            }
        }
    }
}