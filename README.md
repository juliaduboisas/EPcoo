# EP1 - Computação Orientada a Objetos

**Alunos:**
- Caique Alves de Souza (14563126)
- Gustavo Ferreira Botelho de Sena (13686643)
- Júlia Du Bois Araújo Silva (14584360)
- William Jun Okinaka Suzuki (14612440)

## Instruções de compilação:

Utilizando maven:

```
cd ep && mvn clean package && mvn -Dexec.mainClass=coo.ep.Game exec:java
```

Utilizando apenas javac:

```
mkdir ep/target/classes/coo/ep -p && javac -d ep/target/classes/coo/ep/ ep/src/main/java/coo/ep/*.java && java -cp ep/target/classes/coo/ep coo.ep.Game
```

Recomendado utilizar o maven.