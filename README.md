# Tarea Chat Instantaneo

## Demostración del codigo en ejecución.

![image](https://github.com/user-attachments/assets/c8ae7677-c777-4449-8f33-4f969ce9ad30)

Para iniciar el codigo se debe correr una instancia de las clases 'ChatClienteGUI' y 'ChatServidorGUI´en cada una de las terminales, y darle permiso a la firewall de Windows a Netbeans para que se ejecute el codigo.

Una vez ejecutado despliega una interfaz grafica sencilla para el servidor y para el cliente. Una vez ejecutados daran un mensaje para señalizar que la conexión fue exitosa.

![image](https://github.com/user-attachments/assets/9ffbfa56-0f5c-4501-ba0e-aac247e29756) // ![image](https://github.com/user-attachments/assets/49b54a85-8c91-4cb1-a249-35d4095a5985)

Una vez se muestre este mensaje la aplicación ya esta lista para ser usada y los mensajes apareceran instantaneamente en la ventana del servidor/cliente.

![image](https://github.com/user-attachments/assets/984f77d5-4816-4ab3-a5c9-544065c4337b) // ![image](https://github.com/user-attachments/assets/427dca50-04d7-4b8e-898f-0053e04ff381)

## Explicacion del codigo

### Conexion del cliente con el servidor

Se conectan por medio de ´Sockets´ escuchando un puerto especifico en este caso (12345). Por medio de ServerSocket se establece la conexión y por medio de Socket se acepta.

![image](https://github.com/user-attachments/assets/c592f0ec-4ee4-407e-bad9-f4fcd73756d2)

En el cliente por otro lado, tambien se conecta a travez de ´Socket´con la IP ´localhost´y puerto ´12345´

### Envio y recepcion de datos

En el servidor se usa ´PrintWriter´ para enviarle mensajes al cliente y ´BufferedReader´ para recibir mensajes de este.

![image](https://github.com/user-attachments/assets/be299789-aea7-41de-931b-5dafa68dec62)

Y el cliente se maneja de manera similar con ligeros cambios.

![image](https://github.com/user-attachments/assets/0ff61835-6449-49f3-84ed-c50159a44e69)

### Interfaz grafica

Usa componentes principales como JTextArea no editable para que no se pueda editar el historial de chat, JTextField para que el usuario pueda escribir mensajes y un Jbutton para enviar mensajes.










