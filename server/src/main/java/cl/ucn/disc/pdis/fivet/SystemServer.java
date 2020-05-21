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

package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.zeroice.model.Sistema;
import com.zeroc.Ice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server implementation.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings("UtilityClass")
public final class SystemServer {

    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(SystemServer.class);

    /**
     * Empty constructor.
     */
    private SystemServer() {
        // Nothing here.
    }

    /**
     * @param args to use.
     */
    public static void main(final String[] args) {

        log.debug("Starting the Server ..");

        // The communicator
        try (Communicator communicator = Util.initialize(getInitializationData(args))) {

            // The adapter
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("TheAdapter", "default -p 8080 -z");

            // Inline implementation (Lambda)
            Sistema theSystem = (clientTime, current) -> System.currentTimeMillis() - clientTime;

            // The unique identity
            Identity identity = Util.stringToIdentity(Sistema.class.getName());
            log.debug("Using name [{}] and category [{}] as identity.", identity.name, identity.category);

            // Register the API in the framework
            adapter.add(theSystem, identity);
            adapter.activate();

            // .. waiting.
            log.debug("Waiting for connections ..");
            communicator.waitForShutdown();
        }

        log.debug("Server ended!");

    }

    /**
     * @param args to use as source.
     * @return the {@link InitializationData}.
     */
    private static InitializationData getInitializationData(String[] args) {

        // Properties
        final Properties properties = Util.createProperties(args);
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.fivet.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        properties.setProperty("Ice.Trace.Admin.Properties", "1");
        properties.setProperty("Ice.Trace.Locator", "2");
        properties.setProperty("Ice.Trace.Network", "3");
        properties.setProperty("Ice.Trace.Protocol", "1");
        properties.setProperty("Ice.Trace.Slicing", "1");
        properties.setProperty("Ice.Trace.ThreadPool", "1");
        properties.setProperty("Ice.Compression.Level", "9");
        properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.fivet.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

}
