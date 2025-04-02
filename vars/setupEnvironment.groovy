def call() {
    bat """
        @echo off
        echo ⚙️ [1/5] Configurando ambiente Node.js/TypeScript...

        :: 1. Verifica versões do Node.js e npm/yarn
        echo 📌 Verificando Node.js e npm...
        node --version
        npm --version

        :: 2. Limpeza de artefatos anteriores (opcional)
        echo ♻️ Limpando arquivos temporários...
        rmdir /s /q node_modules 2>nul || echo "Nenhum node_modules encontrado"
        del package-lock.json 2>nul || echo "Nenhum package-lock.json encontrado"
        del yarn.lock 2>nul || echo "Nenhum yarn.lock encontrado"

        :: 3. Configuração de variáveis de ambiente
        echo 🔧 Configurando variáveis...
        set NODE_OPTIONS=--max_old_space_size=4096
        set CI=true

        :: 4. Instalação global de dependências (se necessário)
        echo 📦 Instalando TypeScript globalmente (se não estiver presente)...
        npm list -g typescript || npm install -g typescript

        :: 5. Validação final
        echo ✅ Ambiente configurado com sucesso!
        echo "PATH: %PATH%"
    """
}
