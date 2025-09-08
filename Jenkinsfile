pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'  // Make sure this matches your Maven installation in Jenkins
        jdk 'JDK-17'         // Make sure this matches your JDK installation in Jenkins
    }
    
    environment {
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Checking if pom.xml exists...'
                sh 'ls -la' 
                
                echo 'Building the application...'
                sh 'mvn compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
            post {
                always {
                    // Publish test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging the application...'
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    // Archive the built artifacts
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        
        stage('Code Quality Check') {
            steps {
                echo 'Running code quality checks...'
                sh 'mvn verify'
            }
        }
        
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to staging environment...'
                sh '''
                    echo "Stopping existing application if running..."
                    pkill -f "demo-1.0.0.jar" || true
                    
                    echo "Starting new application..."
                    nohup java -jar target/demo-1.0.0.jar --server.port=8080 > app.log 2>&1 &
                    
                    echo "Waiting for application to start..."
                    sleep 10
                    
                    echo "Testing application health..."
                    curl -f http://localhost:8080/health || exit 1
                '''
            }
        }
        
        stage('Integration Tests') {
            steps {
                echo 'Running integration tests...'
                sh '''
                    echo "Testing application endpoints..."
                    curl -f http://localhost:8080/ || exit 1
                    curl -f http://localhost:8080/hello || exit 1
                '''
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed!'
            // Clean workspace
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
            // You can add email notifications here
        }
        failure {
            echo 'Pipeline failed!'
            // You can add email notifications or Slack alerts here
        }
    }
}
