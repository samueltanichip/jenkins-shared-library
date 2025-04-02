def call() {
    bat """
        echo ⚙️ [1/4] Configurando ambiente de teste...
        set NODE_OPTIONS=--max_old_space_size=4096
        node --version
        npm --version
        
        echo ♻️ [2/4] Limpeza prévia...
        del package-lock.json 2>nul || echo "Nenhum package-lock.json"
        del test-results.log 2>nul || echo "Nenhum log de teste anterior"
        
        echo 📦 [3/4] Instalando dependências...
        npm install --loglevel=verbose > npm-install.log 2>&1 || (
            echo "❌ ERRO: Falha na instalação"
            type npm-install.log
            exit 1
        )
        
        echo 🧪 [4/4] Executando testes...
        npm test > test-results.log 2>&1 && (
            echo "✅ Testes executados com sucesso"
            type test-results.log
        ) || (
            echo "❌ Testes falharam"
            type test-results.log
            exit 1  // Falha o pipeline em caso de testes com erro
        )
    """
}
