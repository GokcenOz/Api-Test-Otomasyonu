package pojos;

public class PojoBookingDates {
    // 1- tum variable'lari private olarak olustur
    private String checkin;
    private String checkout;

    // 2- getter ve setter

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
    // 3- parametreli constructor

    public PojoBookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    //4- parametresiz constructor

    public PojoBookingDates() {
    }
    // 5- toString

    @Override
    public String toString() {
        return "PojoBookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
