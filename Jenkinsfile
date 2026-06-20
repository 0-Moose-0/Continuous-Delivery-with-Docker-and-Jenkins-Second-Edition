pipeline {
    agent {
        label 'docker-agent'
    }

    stages {
        stage('Prepare environment') {
            steps {
                sh '''
                    cd Chapter08/sample1
                    chmod +x gradlew
                '''
            }
        }

        stage('Run unit tests') {
            steps {
                sh '''
                    cd Chapter08/sample1
                    ./gradlew test
                '''
            }
        }

        stage('Run code coverage') {
            when {
                changeset "**/*.java"
            }

            steps {
                sh '''
                    cd Chapter08/sample1
                    ./gradlew jacocoTestCoverageVerification
                    ./gradlew jacocoTestReport
                '''

                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Report',
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }

        stage('Run Checkstyle') {
            when {
                changeset "**/*.java"
            }

            steps {
                sh '''
                    cd Chapter08/sample1
                    ./gradlew checkstyleTest
                '''

                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/checkstyle',
                    reportFiles: 'test.html',
                    reportName: 'Checkstyle Report',
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
    }

    post {
        success {
            echo 'pipeline ran perfectly'
        }

        failure {
            echo 'pipeline failure'
        }
    }
}
'n// trigger after branch correction
