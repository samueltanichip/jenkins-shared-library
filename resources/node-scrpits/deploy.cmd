@echo off
set ENV=%1

echo Executing deploy for environment: %ENV%
npm run deploy:%ENV%

if %ERRORLEVEL% neq 0 (
    echo ❌ Deploy failed!
    exit /b 1
)

echo ✅ Deploy completed successfully.
