def call() {
    bat """
        echo ⚙️ [1/5] Configurando ambiente...
        set NODE_OPTIONS=--max_old_space_size=4096
        node --version
        npm --version
        
        echo ♻️ [2/5] Limpando instalações anteriores...
        rmdir /s /q build 2>nul || echo "Nenhum build anterior"
        del package-lock.json 2>nul || echo "Nenhum package-lock.json"
        
        echo 📦 [3/5] Instalando dependências...
        npm install --loglevel=verbose > npm-install.log 2>&1 || (
            echo "❌ ERRO: Falha na instalação"
            type npm-install.log
            exit 1
        )
        
        echo 🛠️ [4/5] Executando build...
        npm run build > npm-build.log 2>&1 || (
            echo "❌ ERRO: Falha no build"
            type npm-build.log
            exit 1
        )
        
        echo 🔍 [5/5] Verificando resultados...
        if not exist build (
            echo "❌ ERRO CRÍTICO: Diretório build não foi criado!"
            echo "Conteúdo atual:"
            dir
            exit 1
        )
        echo "✅ Build gerado com sucesso!"
        dir build
    """
}
