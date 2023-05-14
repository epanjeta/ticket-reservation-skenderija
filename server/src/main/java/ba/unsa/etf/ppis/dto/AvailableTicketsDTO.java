package ba.unsa.etf.ppis.dto;

public class AvailableTicketsDTO {

    private Integer eventId;

    private Integer parterTickets;

    private Integer vipTickets;

    private Integer backstageTickets;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getParterTickets() {
        return parterTickets;
    }

    public void setParterTickets(Integer parterTickets) {
        this.parterTickets = parterTickets;
    }

    public Integer getVipTickets() {
        return vipTickets;
    }

    public void setVipTickets(Integer vipTickets) {
        this.vipTickets = vipTickets;
    }

    public Integer getBackstageTickets() {
        return backstageTickets;
    }

    public void setBackstageTickets(Integer backstageTickets) {
        this.backstageTickets = backstageTickets;
    }
}
