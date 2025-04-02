def call() {
    bat """
        echo ✅ [1/4] Limpando instalações anteriores...
        rmdir /s /q build 2>nul || echo "Diretório build não existia"
        
        echo ✅ [2/4] Instalando dependências...
        npm install --loglevel verbose
        
        echo ✅ [3/4] Executando build...
        set NODE_OPTIONS=--max_old_space_size=4096
        npm run build || (
            echo "❌ Build falhou! Verifique os erros acima"
            exit 1
        )
        
        echo ✅ [4/4] Verificando saída do build...
        if exist build (
            echo "✔ Build gerado em build/"
            dir build
        ) else (
            echo "❌ ERRO: Diretório build não foi criado!"
            dir
            exit 1
        )
    """
}
