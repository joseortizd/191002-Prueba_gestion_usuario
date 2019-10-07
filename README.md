# Gestión de usuario - Prueba Ingreso

## Configuración base de datos

 - Genera la conexión con mysql a través del puerto 3306
 - Se conecta a dos bases de datos, una para la ejecución normal de la aplicación y una para las pruebas.
 - Los nombres respectivos son:  **db_ejercicio**  y **db_ejercicio_tests**
 - Para cambiar la configuración editar los archivos **application.properties** en las rutas:

> /191002-Prueba_gestion_usuario/ejercicio/src/main/resources

y

> /191002-Prueba_gestion_usuario/ejercicio/src/test/resources

## Archivos dentro del proyecto
- Dentro del repositorio dentro de la carpeta Files se encuentran los siguientes archivos referentes al proyecto:

    - Preguntas Teoricas.pdf: Contiene las respuesta a las dos preguntas planteadas en el enunciado del ejecicio.

    - Dump20191006.zip (Ubicado en la siguiente carpeta compartida: https://drive.google.com/open?id=1ZgxMrV3lcBR8TPvQINPXGcSz2VbV5o5X ): Contiene data de prueba para la base de datos db_ejercicio con mas de un millón de registros para la prueba de latencia con esta cantidad de datos.

## LLenar la base de datos con la información de prueba

- Para llenar la base de datos existen dos opciones, la primera es restaurar dentro **db_ejercicio** el Dump mencionado en la sección anterior.

- Otra opción es dentro del código fuente específicamente el archivo **/ejercicio/src/main/java/com/falabella/ejercicio/EjercicioApplication.java**, al quitar los comentarios el código quedaría asi: 

```
@SpringBootApplication
public class EjercicioApplication implements CommandLineRunner{
						
    @Autowired
    UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(EjercicioApplication.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<UserEntity> list = new ArrayList();
        for (int iteration=0; iteration <=4; iteration++) {
            System.err.println(new Date());

            for (int i=1; i <= 300000; i++) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(faker.name().firstName());
                userEntity.setLastName(faker.name().lastName());
                userEntity.setEmail(faker.bothify("??????###@gmail.com"));
                userEntity.setDocument(faker.number().digits(200).toString());
                userEntity.setCreatedAt(new Date());
                userEntity.setAddress(faker.address().fullAddress());
                userEntity.setPhoneNumber(faker.phoneNumber().cellPhone());
                userEntity.setStatus(true);
                userEntity.setUpdatedAT(new Date());
                userEntity.setCreatedAt(new Date());
                list.add(userEntity);
            }        
            userRepository.saveAll(list);
            list.clear();
        }

    }
}
```

Al ejecutar la aplicación se llenará la base de datos en lotes de 300.000 registros. 


## Credenciales de Google Cloud

Dentro del proyecto en las rutas:

> /191002-Prueba_gestion_usuario/ejercicio/src/main/resources/googleGCP11.json


y 


>/191002-Prueba_gestion_usuario/ejercicio/src/test/resources/googleGCP11.json

Descomprimir el archivo de credenciales de Google Cloud, no públicos dentro del repositorio por motivos de seguridad (Adjuntos dentro del correo de entrega).

