package pl.reservationmanager.crm;

import pl.reservationmanager.validation.IsInteger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrmService {

    private Long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String name;

    @IsInteger(value = "price")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String price;

    @IsInteger(value = "duration")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String duration;

    public CrmService() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
