package com.sonderben.sdbvideoapi.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.Utiles.Utile;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@MappedSuperclass
@Entity
public class Subtitle extends BaseEntity implements Serializable {
    @Override
    public String toString() {
        return "Subtitle{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", uploadDate=" + uploadDate +
                ", author='" + author + '\'' +
                '}';
    }

    @Column(length = 3)
    String language;
    @Column(unique = true)
    @Pattern(regexp = "((http|https)://)(www.)?" +
            "[a-zA-Z0-9@:%._\\+~#?&///=]" +
            "{2,256}\\.[a-z]" +
            "{2,6}\\b([-a-zA-Z0-9@:%" +
            "._\\+~#?&//=]*)",message = "bad subtitle url")
    String subtitle;
    Calendar uploadDate;
    String author;
    @JsonProperty("uploadDate")
    private void unpackNested_(String uploadDate){
        this.uploadDate= Utile.unpackNestedDate(uploadDate);
    }
}
