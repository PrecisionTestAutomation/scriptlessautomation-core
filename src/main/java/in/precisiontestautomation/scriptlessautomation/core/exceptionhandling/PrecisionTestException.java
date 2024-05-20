package in.precisiontestautomation.scriptlessautomation.core.exceptionhandling;

/**
 * <p>PrecisionTestException class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
@SuppressWarnings("serial")
public class PrecisionTestException extends RuntimeException{

    private static final String ELEMENT_NOT_VISIBLE = " is not visible on page";
    private static final String ELEMENT_INVALID = " is not present in %s.properties";

		/**
		 * <p>Constructor for PrecisionTestException.</p>
		 *
		 * @param exceptionDetails a {@link String} object
		 */
		public PrecisionTestException(String exceptionDetails){
            super(exceptionDetails);
        }

        /**
         * <p>elementNotVisible.</p>
         *
         * @param elementName a {@link String} object
         * @param t a {@link Throwable} object
         * @return a {@link PrecisionTestException} object
         */
        public static PrecisionTestException elementNotVisible(String elementName, Throwable t){
             return new PrecisionTestException(elementName + ELEMENT_NOT_VISIBLE+ " | "+t.getLocalizedMessage());
        }

    /**
     * <p>invalidElementName.</p>
     *
     * @param elementName a {@link String} object
     * @param pageName a {@link String} object
     * @param t a {@link Throwable} object
     * @return a {@link PrecisionTestException} object
     */
    public static PrecisionTestException invalidElementName(String elementName, String pageName, Throwable t){
        return new PrecisionTestException(" Element name \""+elementName+"\"" + String.format(ELEMENT_INVALID,pageName)+ " | "+t.getLocalizedMessage());
    }

}
