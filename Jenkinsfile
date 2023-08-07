node {
    def app

    stage('Clone repository') {
        checkout scm
    }

    stage('Build image') {
       app = docker.build("wbxsxu168/myresumek8s")
    }

    stage('Test image') {
        app.inside {
            sh 'echo "myresumeImg testing succesfully "'
        }
    }

    stage('Push image') {        
        docker.withRegistry('https://registry.hub.docker.com', 'mydockhubID') {
            app.push("${env.BUILD_NUMBER}")
        }
    }
    
    stage('Trigger ManifestUpdate') {
                echo "myresumek8s app: triggering updatemanifestjob"
                build job: 'myresumek8smanifestjob2', parameters: [string(name: 'DOCKERTAG', value: env.BUILD_NUMBER)]
        }
}
