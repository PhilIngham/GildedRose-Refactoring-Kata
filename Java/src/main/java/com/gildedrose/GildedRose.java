package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Sulfuras, Hand of Ragnaros":
                    updateSulfuras();
                    break;
                case "Aged Brie":
                    updateBrie(item);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateTicket(item);
                    break;
                default:
                    updateCommonItem(item);
            }
        }
    }

    private void decreaseSellByDate(Item item) {
        item.sellIn--;
    }

    private void increaseQuality(Item item) {
        item.quality++;
        item.quality = Math.min(item.quality, 50);
    }

    private void decreaseQuality(Item item) {
        item.quality--;
        item.quality = Math.max(item.quality, 0);
    }

    private void updateSulfuras() {
        // Sulfuras never changes, stats remain the same
    }

    private void updateBrie(Item brie) {
        increaseQuality(brie);
        decreaseSellByDate(brie);
    }

    private void updateTicket(Item ticket) {
        int dateOfConcert = ticket.sellIn;
        if(dateOfConcert < 0){ // Concert has passed and ticket no longer holds value
            ticket.quality = 0;
            decreaseSellByDate(ticket);
            return;
        }

        increaseQuality(ticket);
        if (dateOfConcert < 11){
            increaseQuality(ticket);
            if(dateOfConcert < 6) increaseQuality(ticket);
        }
        decreaseSellByDate(ticket);
    }

    private void updateCommonItem(Item item) {
        decreaseQuality(item);
        if(item.sellIn < 0){
            decreaseQuality(item);
        }
        decreaseSellByDate(item);
    }


}
