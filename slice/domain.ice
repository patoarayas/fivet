/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya González <patricio.araya@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// https://doc.zeroc.com/ice/3.7/language-mappings/java-mapping/client-side-slice-to-java-mapping/customizing-the-java-mapping
["java:package:cl.ucn.disc.pdis.fivet.zeroice"]
module model {

    /**
     * The base system.
     */
     interface TheSystem {

        /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);

     }

     /**
     * Persona
     */
     class Persona{

        // PK
        long id;
        // Rut
        string rut;
        // Nombre
        string nombre;
        // address
        string direccion;
        // Telefono
        long telefono;
        // Número télefono celular
        long celular;
        // email
        string email;

     }

     /**
     *  Ficha de paciente
     */
     class Ficha {

         // Id
         int id;
         // Numero de registro
         int numero;
         // name
         string nombre;
         // Especie
         string especie;
         // Fecha de nacimiento
         string fechaNacimiento;
         // Raza
         string raza;
         // Sexo
         string sexo;
         // Color
         string color;
         // Tipo
         string tipo;

     }
     /**
     * Clase Control
     */
     class Control{

        // Fecha
        string fechaControl;
        // Fecha proximo control
        string fechaProximoControl;
        // Temperatura
        double temperatura;
        // Peso
        double peso;
        // Altura
        double altura;
        // Diagnostico
        string diagnostico;
        // Veterinario TODO: Veterinario es persona?
        string veterinario;


     }


}
