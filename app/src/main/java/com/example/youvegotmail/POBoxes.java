package com.example.youvegotmail;

/**
 * Data model for each row of the RecyclerView
 */

class POBoxes {

    // Member variables representing the title and information about the p.o.box.
    private String title;
    private String info;
    private final int imageResource;

    POBoxes(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    int getImageResource() {
        return imageResource;
    }
}
