pipeline {
    agent {
        label 'docker-agent'
    }

    stages {
        stage('Checkout code and prepare environment') {
            steps {
                git url: 'https://github.com/0-Moose-0/Continuous-Delivery-with-Docker-and-Jenkins-Second-Edition.git',
                    branch: 'master'

                sh '''
                    cd Chapter08/sample1
                    chmod +x gradlew
                '''
            }
        }

        stage('Run unit tests and coverage') {
            steps {
                sh '''
                    cd Chapter08/sample1
                    ./gradlew test
                    ./gradlew jacocoTestCoverageVerification
                    ./gradlew jacocoTestReport
                '''
            }
        }

        stage('Run Checkstyle') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh '''
                        cd Chapter08/sample1
                        ./gradlew checkstyleTest
                    '''
                }
            }

            post {
                always {
                    publishHTML(target: [
                        reportDir: 'Chapter08/sample1/build/reports/checkstyle',
                        reportFiles: 'test.html',
                        reportName: 'jacoco checkstyle',
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true
                    ])
                }
            }
        }
    }
}