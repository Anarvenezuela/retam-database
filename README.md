# Aplicación de escritorio de la base de datos RETAM

La aplicación está compuesta de dos módulos Maven relativamente independientes.

## Retam Migration
Este modulo permite tomar los datos extraídos de la base de datos de la aplicación original de RETAM para luego importarlos en una base de datos que será utilizada en la aplicación de escritorio. El fin de este modulo es de tomar el esquema de base de datos redundante y poco normalizado de la aplicación original y convertirlo en un esquema relacional más sencillo con menos problemas de redundancia.

### Tecnologías usadas

- SQLite: es una base de datos que almacena la información en un archivo. Esto lo hace altamente portable y hace que los usuarios no necesiten instalar una base de datos para ejecutar la aplicación. 
- Java 8: La ultima versión de Java hace unos años. Podría pasarse a una versión más nueva sin problemas
- OpenCSV: Lee archivos CSV y los convierte en POJOs Java.

### Ejecución del módulo

Antes de ejecutar la aplicación una base de datos debe ser creada usando el script
 *createTables.sql* en SQLite (se hace fácilmente vía linea de comandos). Un script para borrar las tablas también se encuentra en este modulo.

La aplicación se ejecuta como una aplicación Java vía su clase Main y realiza los siguientes pasos:
- Lee los archivos CSV situados en la carpeta resources para convertirlos en clases "OldModels".
- Vía clases "Migrators" los "OldModels" son transformados y almacenados en la base de datos SQLite
  - Los "Migrators" se ejecutan en un orden específico ya que algunos Migrators dependen de los datos migrados anteriormente.
  - Algunos "Migrators" modifican los datos de manera un poco arbitraria. Esto se debe a que el esquema de la base de datos original es altamente redundante y muchas de las entidades están repetidas o relacionadas con entidades equivocadas. No hay manera determinista de resolver estos conflictos, el proyecto intenta resolverlos de la mejor manera posible.
    - Por ejemplo, los nombres de las entidades presentan bastantes conflictos, muchas entidades usan a veces abreviaciones y otras veces no. El proyecto se toma la libertad de decir que si dos entidades tienen el mismo ID el nombre más largo es el que sera almacenado en la base de datos final, suponiendo que es el nombre más completo.
- El resultado es una base de datos SQLite con un nuevo esquema que puede ser usado para la aplicación de escritorio.
  
### Obtención de los archivos

Los archivos CSV se obtienen mediante la extracción de los datos de manera manual de la base de datos original de la RETAM. El proceso de acceso a la base de datos de la aplicación original no está documentado. Pero aquí algunas pistas que pueden ayudar:

- Primero se necesita instalar la aplicación original de la RETAM (disponible vía CD). Esta aplicación es unicamente compatible con Windows XP, hoy en día una computadora con Windows XP es difícil de conseguir, lo más sencillo es usar una máquina virtual (como VirtualBox) e instalar la aplicación.
- La base de datos es una base de datos Microsoft SQL Server, creo que la aplicación instala SQL server automáticamente. Pero tengo un vago recuerdo de haber instalado SQL Server 2008 (o la ultima disponible para XP) para conectarme.
- No recuerdo si la base de datos tenia una clave. O era de libre acceso, o tenia la contraseña por defecto, o el usuario y la contraseña se podían encontrar en el código de la aplicación.

Nótese que en el módulo hay dos tipos de CSV separados por en dos carpetas.
- Carpeta As Is: Datos exportados tal cual como se encontraban en la base de datos original (export de la tabla vía SELECT *)
- Carpeta query: Datos exportados vía queries específicos para facilitar la migración. El query utilizado se encuentra en esta carpeta también.

Algunos de los datos extraidos también fueron modificados *manualmente* para para mejorar le coherencia de datos. 

## Retam Database

Este modulo toma la base de datos SQLite generada por el módulo anterior y la muestra visualmente en una aplicación de escritorio.

### Tecnologías usadas
- Java 7: A diferencia del módulo anterior, éste utiliza Java 7. Uno de los requerimientos principales de la aplicación era de poderse ejecutar en computadoras Windows XP. La ultima versión de Java compatible con Windows XP es Java 7.
- JavaFX: Una librería (antiguamente de Oracle pero hoy en día OpenSource) que permite realizar aplicaciones de escritorio usando código Java. Java 7 es compatible con JavaFX 1. JavaFX 1, a diferencia de JavaFX 2 (disponible a partir de Java 8), tiene varios bugs y esta poco documentada. A través del tiempo la librería a caído en desuso y la documentación es escasa.
- Netbeans: Al momento de realizar esta aplicación, Netbeans contaba con un IDE visual para crear aplicaciones en JavaFX. Visto que JavaFX ya no es una librería propietaria de Oracle quizás Netbeans no cuente más con esta funcionalidad. Al parecer Eclipse tiene una librería JavaFX.

### Ejecución del módulo
La aplicación se ejecuta como una aplicación Java vía su clase Main

## Lo que queda por hacer
En la base de datos original aún quedaban tablas por exportar. Habría que:
- Diseñar el resto de la base de datos de la nueva aplicación.
- Hacer nuevos exports de csv que correspondan a este nuevo modelo.
- Verificar los datos manualmente (esto es lo que toma más tiempo).
- Hacer nuevos "Migrators" que tomen estos datos y los importen en la nueva base de datos.
- Si lo desean podrian migrar el proyecto a un proyecto Spring, lo cual podria facilitarles algunas partes del codigo.

La parte visual:
- JavaFX ha caído en desuso y tiene muchos bugs. Quedaría por ver si vale la pena continuar con esta tecnología o usar una más reciente (y sobre todo verificar si la aplicación tiene que ser ejecutable en Windows XP, esto podría abrir o cerrar las puertas a otras tecnologías más recientes).
- Hacer la visualización de los nuevos datos importados.
