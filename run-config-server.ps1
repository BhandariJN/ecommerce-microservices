# Run this script to start the Config Server
param([switch]$Background)

$scriptPath = $MyInvocation.MyCommand.Path
$scriptDir = Split-Path $scriptPath
$configServerDir = Join-Path $scriptDir "services\config-server"

Write-Host "Starting Config Server from $configServerDir..."
Push-Location $configServerDir

# Set JAVA_HOME if needed
# $env:JAVA_HOME = "C:\Users\JN\.jdks\ms-17.0.18"

if ($Background) {
    Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory $configServerDir -NoNewWindow
} else {
    .\mvnw.cmd spring-boot:run
}

Pop-Location

