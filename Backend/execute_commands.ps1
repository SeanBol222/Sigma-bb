# ========================
# run-pipeline.ps1
# Usage: .\run-pipeline.ps1 -CommandsFile modelCommands.txt
#        .\run-pipeline.ps1 -CommandsFile modelCommands.txt -StopOnError:$false
# ========================

param(
    [Parameter(Mandatory=$true)]
    [string]$CommandsFile,

    [switch]$StopOnError = $true
)

# ========================
# VALIDATION
# ========================

if (-not (Test-Path $CommandsFile)) {
    Write-Host "[ERROR] File not found: $CommandsFile" -ForegroundColor Red
    exit 1
}

# Read commands (clean up empty lines and comments)
$commands = Get-Content $CommandsFile |
    Where-Object { $_ -match '\S' -and -not $_.Trim().StartsWith("#") }

if (-not $commands -or $commands.Count -eq 0) {
    Write-Host "[WARN] El archivo no contiene comandos ejecutables." -ForegroundColor Yellow
    Write-Host "[WARN] Agrega 1 comando por linea en el archivo y vuelve a ejecutar." -ForegroundColor Yellow
    Write-Host "[WARN] Las lineas que empiezan con # se ignoran." -ForegroundColor Yellow
    exit 1
}

Write-Host "[INFO] Total commands: $($commands.Count)" -ForegroundColor Cyan

# Create logs directory
$logDir = "logs"
if (-not (Test-Path $logDir)) {
    New-Item -ItemType Directory -Path $logDir | Out-Null
}

# ========================
# EXECUTION
# ========================

$success = 0
$fail    = 0
$errors  = @()

for ($i = 0; $i -lt $commands.Count; $i++) {

    $cmd   = $commands[$i].Trim()
    $index = $i + 1

    Write-Host "`n[$index/$($commands.Count)] Executing:" -ForegroundColor Yellow
    Write-Host $cmd -ForegroundColor DarkGray

    $outFile = "$logDir/out_$index.log"
    $errFile = "$logDir/err_$index.log"

    try {
        $processParams = @{
            FilePath               = "cmd.exe"
            ArgumentList           = @('/d', '/s', '/c', $cmd)
            NoNewWindow            = $true
            PassThru               = $true
            Wait                   = $true
            RedirectStandardOutput = $outFile
            RedirectStandardError  = $errFile
            WorkingDirectory       = (Get-Location).Path
        }

        $process = Start-Process @processParams

        if ($process.ExitCode -eq 0) {
            Write-Host "[OK] Success" -ForegroundColor Green

            if (Test-Path $outFile) {
                $output = Get-Content $outFile -ErrorAction SilentlyContinue
                if ($output) {
                    Write-Host "--- Output ---" -ForegroundColor DarkCyan
                    $output | Select-Object -First 10
                }
            }

            $success++
        }
        else {
            $errorText = ""
            if (Test-Path $errFile) {
                $errorText = Get-Content $errFile -ErrorAction SilentlyContinue | Out-String
            }
            throw "ExitCode: $($process.ExitCode)`n$errorText"
        }

    } catch {
        Write-Host "[ERROR] Failed" -ForegroundColor Red
        Write-Host $_ -ForegroundColor Red

        $fail++
        $errors += @{
            Index   = $index
            Command = $cmd
            Error   = $_.ToString()
        }

        if ($StopOnError) {
            Write-Host "`n[STOP] Execution halted due to error" -ForegroundColor Red
            break
        }
    }
}

# ========================
# SUMMARY
# ========================

Write-Host ("`n" + ("=" * 60)) -ForegroundColor Cyan
Write-Host "EXECUTION SUMMARY" -ForegroundColor Cyan
Write-Host ("=" * 60) -ForegroundColor Cyan

Write-Host "[+] Successful : $success" -ForegroundColor Green
Write-Host "[-] Failed     : $fail"    -ForegroundColor Red

if ($errors.Count -gt 0) {
    Write-Host "`n--- Errors ---" -ForegroundColor Red
    foreach ($err in $errors) {
        Write-Host "[$($err.Index)] $($err.Command)" -ForegroundColor Yellow
        Write-Host $err.Error -ForegroundColor Red
    }
}

Write-Host ("=" * 60) -ForegroundColor Cyan

exit $fail