package me.obleci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.obleci.entity.Item;

@Data
public class ItemAvailableBean {

    @JsonProperty("i_id")
    private Long id;

    @JsonProperty("s")
    private Item.ItemStatus status;

    public ItemAvailableBean() {

    }
}
