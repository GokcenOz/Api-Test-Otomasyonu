package pojos;

public class PojoBookingBody {
    // 1- private variable'lar
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private PojoBookingDates bookingdates;
    private String additionalneeds;
    //2- getter setter


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public PojoBookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(PojoBookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    // 3 - parametreli constructor

    public PojoBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid, PojoBookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
    // 4- parametresiz constructor

    public PojoBookingBody() {
    }

    // 5 - toString

    @Override
    public String toString() {
        return "PojoBookingBody{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}

