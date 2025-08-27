// Función para realizar solicitud GET asincrónica
async function performGet(event) {
    event.preventDefault();
    const name = document.getElementById('getName').value; // nombre ingresado

    try {
        // Solicitud GET al servidor
        const response = await fetch(`/api/hello?name=${encodeURIComponent(name)}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const text = await response.text();
        const data = text ? JSON.parse(text) : {};

        // Genera la respuesta personalizada en el cliente
        const message = `¡Hola, ${name}!`;
        document.getElementById('getResponse').innerHTML = message;

    } catch (error) {
        console.error('Error en la solicitud GET:', error);
        document.getElementById('getResponse').innerHTML = `¡Hola, ${name}! Hubo un problema al procesar tu solicitud.`;
    }
}

// Función para realizar solicitud POST asincrónica
async function performPost(event) {
    event.preventDefault();
    const newName = document.getElementById('postName').value;
    try {
        const response = await fetch('/api/updateName', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: newName }) // nombre actualizado
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.text();

        // Muestra el nombre actualizado en la respuesta
        document.getElementById('postResponse').innerHTML = `Tu nombre ha sido actualizado a: "${newName}". ${data}`;

        // Actualiza el valor del campo 'getName' con el nuevo nombre
        const message = `¡Hola, ${newName}!`;
        document.getElementById('getResponse').innerHTML = message;

    } catch (error) {
        console.error('Error en la solicitud POST:', error);
        document.getElementById('postResponse').innerHTML = `¡Error! No se pudo actualizar el nombre: ${error.message}`;
    }
}

// Event listeners para los formularios
document.getElementById('getForm').addEventListener('submit', performGet);
document.getElementById('postForm').addEventListener('submit', performPost);

