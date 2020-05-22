/*
 * MIT License
 *
 * Copyright (c) 2020 Patricio Araya González <patricio.araya@alumnos.ucn.cl>.
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

package cl.ucn.disc.pdis.fivet.dao;

import cl.ucn.disc.pdis.fivet.model.Persona;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Test para la db
 */

public class DatabaseTest {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(DatabaseTest.class);

    /**
     * Test DB functionality
     * @throws SQLException .
     */
    @Test
    final void testDatabase() throws SQLException {

        // Database Url
        String dbUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(dbUrl)) {
            // create table
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // Create DAO
            Dao<Persona, Integer> daoPersona = DaoManager.createDao(connectionSource, Persona.class);

            // Create a Persona object
            Persona persona = new Persona("rut", "persona1", "dir", 999999, 99999, "email");

            // Insert into DB
            int rows = daoPersona.create(persona);

            // Test insertion
            Assertions.assertEquals(1, rows, "Updated rows greater than 1");

            // Test query

            Persona personaDb = daoPersona.queryForId(persona.id);
            Assertions.assertEquals(persona.getNombre(), personaDb.getNombre(), "Nombre don't match");
            Assertions.assertEquals(persona.getRut(), personaDb.getRut(), "Rut don't match");

            // Test query by rut
            List<Persona> personaList = daoPersona.queryForEq("rut", persona.getRut());
            Assertions.assertEquals(1, personaList.size(), "Query result should be 1, It wasn't");

            // Test worng rut
            Assertions.assertEquals(0, daoPersona.queryForEq("rut", "Badrut").size(), "Result shoudl be 0");

        } catch (IOException e) {
            log.error("IO exception");
        }

    }
}
