def call() {
    bat """
        echo ✅ [1/4] Limpando ambiente...
        rmdir /s /q build 2>nul || echo "Nenhum build anterior encontrado"
        del package-lock.json 2>nul || echo "Nenhum package-lock.json encontrado"

        echo ✅ [2/4] Instalando dependências...
        npm install --loglevel=verbose > install.log 2>&1 || (
            echo "❌ Falha na instalação"
            type install.log
            exit 1
        )

        echo ✅ [3/4] Executando build...
        set NODE_OPTIONS=--max_old_space_size=4096
        npm run build > build.log 2>&1 || (
            echo "❌ Build falhou!"
            type build.log
            exit 1
        )

        echo ✅ [4/4] Verificando saída...
        if not exist build (
            echo "❌ ERRO: Diretório build não foi criado!"
            echo "Conteúdo atual:"
            dir
            exit 1
        )
        echo "✔ Build gerado com sucesso em build/"
        dir build
    """
}
