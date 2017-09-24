package entity;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public class Address {
    private static final String DELIMITER = ",";
    private static final String ERROR_MSG = "Too few or too many fields";

    private enum AddressField{
        STREET(0),
        SUITE(1),
        CITY(2),
        STATE(3),
        ZIP(4)
        ;
        int field;

        AddressField(int field) {
            this.field = field;
        }
    }

    private String street;
    private String suite;
    private String city;
    private String state;
    private String zip;

    /**
     *
     * @param addressString contains the entire address with each field separated by the delimiter
     * @return an Address created from this {@link String}
     * @throws WrongNumberArgsException if number of fields doesn't match up
     */
    public static Address newInstance(String addressString) throws WrongNumberArgsException {
        String addressFields [] = addressString.split(Address.DELIMITER);
        if(addressFields.length != AddressField.values().length)
            throw new WrongNumberArgsException(Address.ERROR_MSG);
        return new Address(
                addressFields[AddressField.STREET.field],
                addressFields[AddressField.SUITE.field],
                addressFields[AddressField.CITY.field],
                addressFields[AddressField.STATE.field],
                addressFields[AddressField.ZIP.field]
        );
    }

    public Address(String street, String suite, String city, String state, String zip) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public static String getDELIMITER() {
        return DELIMITER;
    }

    /**
     *
     * @return the entire street as a comma separated value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.street + ",");
        builder.append(this.suite + ",");
        builder.append(this.city + ",");
        builder.append(this.state + ",");
        builder.append(this.zip);
        return builder.toString();
    }
}
