def call(String env = 'staging') {
    bat """
        echo 🚀 Deploying to ${env}...
        call resources\\node-scripts\\deploy.cmd ${env}
    """
}
