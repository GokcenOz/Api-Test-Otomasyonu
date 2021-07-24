package pojos;

public class PojoHerokuappExpectedData {
    // 1- private variable
    private int bookingid;
    private PojoBookingBody booking;
    // 2- getter setter


    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoBookingBody getBooking() {
        return booking;
    }

    public void setBooking(PojoBookingBody booking) {
        this.booking = booking;
    }
    // 3- parametreli constructor

    public PojoHerokuappExpectedData(int bookingid, PojoBookingBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    // 4- parametresiz constructor


    public PojoHerokuappExpectedData() {
    }

    // 5- toString

    @Override
    public String toString() {
        return "PojoHerokuappExpectedData{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
