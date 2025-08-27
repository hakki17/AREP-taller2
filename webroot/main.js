// Función para realizar solicitud GET asincrónica
async function performGet(event) {
    event.preventDefault();
    const name = document.getElementById('getName').value;
    try {
        const response = await fetch(`/app/hello?name=${encodeURIComponent(name)}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.text();
        document.getElementById('getResponse').innerHTML = data;
    } catch (error) {
        console.error('Error en la solicitud GET:', error);
        document.getElementById('getResponse').innerHTML = `¡Error! No se pudo obtener la respuesta: ${error.message}`;
    }
}

// Función para realizar solicitud POST asincrónica
async function performPost(event) {
    event.preventDefault();
    const newName = document.getElementById('postName').value;
    try {
        const response = await fetch('/app/updateName', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: newName })
        });
        document.getElementById('postResponse').innerHTML = response.ok
            ? `Nombre actualizado a: "${newName}"`
            : `Error: ${response.statusText}`;
        // Actualiza el valor del campo 'getName' con el nuevo nombre
            const message = `¡Hola, ${newName}!`;
            document.getElementById('getResponse').innerHTML = message;
    } catch (error) {
        console.error('Error en la solicitud POST:', error);
        document.getElementById('postResponse').innerHTML = `¡Error! ${error.message}`;
    }
}
async function getPi() {
    try {
        // Solicitud GET al servidor para obtener Pi
        const response = await fetch('/app/pi');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const piValue = await response.text();
        document.getElementById('getPi').innerHTML = `El valor de Pi es: ${piValue}`;
    } catch (error) {
        console.error('Error al obtener el valor de Pi:', error);
        document.getElementById('piResponse').innerHTML = `¡Error! No se pudo obtener el valor de Pi: ${error.message}`;
    }
}

async function getHello() {
    try {
        // Solicitud GET al servidor para obtener Pi
        const response = await fetch('/app/mundo');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const hello = await response.text();
        document.getElementById('getHello').innerHTML = `${hello}`;
    } catch (error) {
        document.getElementById('getHello').innerHTML = `¡Error! No se pudo dar Hello World: ${error.message}`;
    }
}



// Event listeners para los formularios
document.getElementById('getForm').addEventListener('submit', performGet);
document.getElementById('postForm').addEventListener('submit', performPost);
document.getElementById('getPiForm').addEventListener('click', getPi);
document.getElementById('getHelloForm').addEventListener('click', getHello);
