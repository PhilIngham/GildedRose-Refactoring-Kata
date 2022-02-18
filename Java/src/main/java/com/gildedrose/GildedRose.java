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

    private void updateSulfuras() {
        // Sulfuras never changes, stats remain the same
    }

    private void updateBrie(Item brie) {
        brie.quality++;
        brie.quality = Math.min(brie.quality, 50);
        brie.sellIn--;
    }

    private void updateTicket(Item ticket) {
        int dateOfConcert = ticket.sellIn;
        if(dateOfConcert < 0){
            ticket.quality = 0;
            ticket.sellIn--;
            return;
        }
        ticket.quality++;
        if (dateOfConcert < 11){
            ticket.quality++;
            if(dateOfConcert < 6){
               ticket.quality++;
            }
        }
        ticket.quality = Math.min(ticket.quality, 50);
        ticket.sellIn--;
    }

    private void updateCommonItem(Item item) {
        item.quality--;
        if(item.sellIn < 0){
            item.quality--;
        }
        item.quality = Math.max(item.quality, 0);
        item.sellIn--;
    }







}
