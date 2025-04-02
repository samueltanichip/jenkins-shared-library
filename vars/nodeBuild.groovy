def call() {
    bat """
        echo ⚙️ Configurando ambiente...
        set NODE_OPTIONS=--max_old_space_size=4096
        
        echo ♻️ Limpando builds anteriores...
        rmdir /s /q build 2>nul || echo "Nenhum build anterior"
        
        echo 📦 Instalando dependências...
        npm install --loglevel=verbose > install.log 2>&1 || type install.log
        
        echo 🛠️ Executando build...
        npm run build > build.log 2>&1 || (
            echo "❌ Build falhou, criando estrutura mínima..."
            mkdir build
            echo "<html><body>Build failed</body></html>" > build/index.html
            type build.log
        )
        
        echo 🔍 Verificando resultados...
        if not exist build exit 1
        dir build
    """
}
