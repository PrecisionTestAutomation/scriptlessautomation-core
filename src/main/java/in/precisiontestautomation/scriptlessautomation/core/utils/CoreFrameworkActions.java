package in.precisiontestautomation.scriptlessautomation.core.utils;

import java.util.Objects;

/**
 * <p>FrameworkActions class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class CoreFrameworkActions {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String KEY = "key";


    /**
     * <p>getProperty.</p>
     *
     * @param variable a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
    public static String getProperty(String variable) {
        return Objects.isNull(System.getProperty(variable)) ?
                null :
                System.getProperty(variable).replaceAll("%20", " ");
    }

}
