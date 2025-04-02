def call() {
    bat """
        echo ⚙️ Configurando ambiente Node.js...
        set NODE_OPTIONS=--max_old_space_size=4096
        node --version
        npm --version
        
        echo ♻️ Limpando instalações anteriores...
        del package-lock.json 2>nul || echo "Nenhum package-lock.json encontrado"
        
        echo 📦 Instalando dependências...
        npm install --loglevel=verbose > npm-install.log 2>&1 || (
            echo "❌ ERRO: Falha na instalação de dependências"
            type npm-install.log
            exit 1
        )
        
        echo ✅ Instalação concluída com sucesso
        dir node_modules
    """
}
