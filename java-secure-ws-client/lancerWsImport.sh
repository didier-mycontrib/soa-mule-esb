# -d src ou -d src/main/java ou ...
#wsimport -d src -keep http://localhost:8080/wsCalculateur/services/calculateur?wsdl
wsimport -d src -keep http://localhost:8081/WSSCalculator?wsdl
echo "fin"; read fin
