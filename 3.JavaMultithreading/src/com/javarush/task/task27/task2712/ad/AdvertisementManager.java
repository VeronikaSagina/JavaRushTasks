package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
/* у каждого планшета будет свой объект менеджера,
   который будет подбирать оптимальный набор роликов
   и их последовательность для каждого заказа.
   Он также будет взаимодействовать с плеером и отображать ролики.

   этот класс выполняет только одно действие - обрабатывает рекламное видео.*/

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        /* 1. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
         * Отобразить все рекламные ролики, отобранные для показа,
         * в порядке уменьшения стоимости показа одного рекламного ролика в копейках. Вторичная сортировка -
         * по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки*/
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }
        List<List<Advertisement>> advertisingToShow = new ArrayList<>();
        List<Advertisement> list = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if (advertisement.getAmountPerOneDisplaying() > 0 && advertisement.getDuration() <= timeSeconds){
                list.add(advertisement);
            }
        }

    }
}
