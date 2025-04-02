def call() {
    bat """
        echo ✅ Building Node.js project...
        npm install
        npm run build
    """
}
