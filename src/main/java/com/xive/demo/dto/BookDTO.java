package com.xive.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

@Data
public class BookDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    @Size(min = 1, max = 200)
    private String description;

    private String first50Paragraphs;
}
