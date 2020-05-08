/*
 * MIT License
 *
 * Copyright (c) 2020 Diego Urrutia-Astorga <durrutia@ucn.cl>.
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

package cl.ucn.disc.pdis.fivet.zeroice;

import com.zeroc.Ice.*;
import org.slf4j.LoggerFactory;

/**
 * Implementacion del logger.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Slf4jLoggerPluginFactory implements PluginFactory {

    /**
     * The logger.
     */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Slf4jLoggerPluginFactory.class);

    /**
     * Called by the Ice run time to create a new plug-in.
     *
     * @param communicator The communicator that is in the process of being initialized.
     * @param name         The name of the plug-in.
     * @param args         The arguments that are specified in the plug-ins configuration.
     * @return The plug-in that was created by this method.
     **/
    @Override
    public Plugin create(Communicator communicator, String name, String[] args) {

        return new LoggerPlugin(communicator, new Slf4jLogger()) {
            /**
             * Called by the Ice run time during communicator initialization. The derived class
             * can override this method to perform any initialization that might be required
             * by a custom logger.
             **/
            @Override
            public void initialize() {
                super.initialize();
                log.debug("Initialize!");
            }

            /**
             * Called by the Ice run time when the communicator is destroyed. The derived class
             * can override this method to perform any finalization that might be required
             * by a custom logger.
             **/
            @Override
            public void destroy() {
                log.debug("Destroy!");
                super.destroy();
            }
        };
    }

    /**
     * Implementacion del server de articulos.
     *
     * @author Diego Urrutia-Astorga.
     */
    public static final class Slf4jLogger implements Logger {

        /**
         * The logger.
         */
        private static final org.slf4j.Logger log = LoggerFactory.getLogger(Slf4jLogger.class);

        /**
         * Print a message. The message is printed literally, without
         * any decorations such as executable name or time stamp.
         *
         * @param message The message to log.
         **/
        @Override
        public void print(String message) {
            log.debug(message);
        }

        /**
         * Log a trace message.
         *
         * @param category The trace category.
         * @param message  The trace message to log.
         **/
        @Override
        public void trace(String category, String message) {
            log.debug(message);
            //log.trace(MarkerFactory.getMarker(category), message);
        }

        /**
         * Log a warning message.
         *
         * @param message The warning message to log.
         * @see #error
         **/
        @Override
        public void warning(String message) {
            log.warn(message);
        }

        /**
         * Log an error message.
         *
         * @param message The error message to log.
         * @see #warning
         **/
        @Override
        public void error(String message) {
            log.error(message);
        }

        /**
         * Returns this logger's prefix.
         *
         * @return The prefix.
         **/
        @Override
        public String getPrefix() {
            return null;
        }

        /**
         * Returns a clone of the logger with a new prefix.
         *
         * @param prefix The new prefix for the logger.
         * @return A logger instance.
         **/
        @Override
        public Logger cloneWithPrefix(String prefix) {
            return null;
        }
    }
}
