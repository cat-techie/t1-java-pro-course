package ru.t1.pmorozov.service;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.pmorozov.dto.LimitReqDto;
import ru.t1.pmorozov.dto.LimitRespDto;
import ru.t1.pmorozov.entity.Limit;
import ru.t1.pmorozov.exceptions.LimitRespException;
import ru.t1.pmorozov.properties.LimitProperties;
import ru.t1.pmorozov.repository.LimitRepo;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@EnableScheduling
public class LimitService {
    private final LimitRepo limitRepo;
    private final LimitProperties limitProperties;

    public LimitService(LimitRepo limitRepo, LimitProperties limitProperties) {
        this.limitRepo = limitRepo;
        this.limitProperties = limitProperties;
    }

    @Transactional
    @Scheduled(cron = "@daily")
    public void resetLimits() {
        limitRepo.findAll().forEach(l -> {
            l.setCurrentValue(l.getMaxValue());
            limitRepo.save(l);
        });
    }

    @Transactional
    public Limit createLimit(Long userId) {
        return limitRepo.save(new Limit(userId, limitProperties.getMinValue(), limitProperties.getMinValue(), LocalDate.now()));
    }

    @Transactional
    public Limit getLimitByUserId(Long userId) {
        Limit limit = limitRepo.findByUserId(userId);
        return limit == null ? createLimit(userId) : limit;
    }

    @Transactional
    public LimitRespDto decreaseCurrentLimit(LimitReqDto limitReqDto) {
        Long userId = limitReqDto.getUserId();
        BigDecimal sum = limitReqDto.getSum();
        Limit limit = getLimitByUserId(userId);

        BigDecimal oldValue = limit.getCurrentValue();
        BigDecimal newValue;

        if (sum.compareTo(oldValue) > 0)
            throw new LimitRespException(String.format("Запрошенная сумма (%s) превышает доступный лимит (%s) для пользователя %s", sum, oldValue, userId), HttpStatus.BAD_REQUEST);

        newValue = oldValue.subtract(sum);
        limit.setCurrentValue(newValue);
        limitRepo.save(limit);

        return new LimitRespDto(userId, oldValue, newValue);

    }

    @Transactional
    public LimitRespDto increaseCurrentLimit(LimitReqDto limitReqDto) {
        Long userId = limitReqDto.getUserId();
        BigDecimal sum = limitReqDto.getSum();
        Limit limit = getLimitByUserId(userId);

        BigDecimal oldValue = limit.getCurrentValue();
        BigDecimal newValue;

        if (oldValue.add(sum).compareTo(limit.getMaxValue()) > 0)
            throw new LimitRespException(String.format("Указанная сумма (%s) превышает максимальный лимит (%s) для пользователя %s", oldValue.add(sum), limit.getMaxValue(), userId), HttpStatus.BAD_REQUEST);

        newValue = oldValue.add(sum);
        limit.setCurrentValue(newValue);
        limitRepo.save(limit);

        return new LimitRespDto(userId, oldValue, newValue);
    }

    @Transactional
    public LimitRespDto increaseMaxUserLimit(LimitReqDto limitReqDto) {
        Long userId = limitReqDto.getUserId();
        BigDecimal sum = limitReqDto.getSum();
        Limit limit = getLimitByUserId(userId);

        BigDecimal oldValue = limit.getMaxValue();
        BigDecimal newValue;
        LocalDate dateNow = LocalDate.now();

        if (dateNow.minusDays(limitProperties.getUpdateDays()).isBefore(limit.getUpdateDate())) {
            throw new LimitRespException(String.format("Последнее изменение максимального лимита было меньше чем (%s) дней назад (%s). Сейчас нельзя повысить лимит",
                    limitProperties.getUpdateDays(), limit.getUpdateDate()), HttpStatus.BAD_REQUEST);
        } else if (limitProperties.getMaxIncrease().compareTo(sum) < 0) {
            throw new LimitRespException(String.format("Указанная сумма (%s) превышает максимально-возможное значение для повышения лимита", sum), HttpStatus.BAD_REQUEST);
        }

        newValue = oldValue.add(sum);
        limit.setMaxValue(newValue);
        limit.setUpdateDate(dateNow);
        limitRepo.save(limit);

        return new LimitRespDto(userId, oldValue, newValue);
    }

    @Transactional
    public LimitRespDto decreaseMaxUserLimit(LimitReqDto limitReqDto) {
        Long userId = limitReqDto.getUserId();
        BigDecimal sum = limitReqDto.getSum();
        Limit limit = getLimitByUserId(userId);

        BigDecimal oldValue = limit.getMaxValue();
        BigDecimal newValue;
        LocalDate dateNow = LocalDate.now();

        newValue = oldValue.subtract(sum);

        if (dateNow.minusDays(limitProperties.getUpdateDays()).isBefore(limit.getUpdateDate())) {
            throw new LimitRespException(String.format("Последнее изменение максимального лимита было меньше чем (%s) дней назад (%s). Сейчас нельзя повысить лимит",
                    limitProperties.getUpdateDays(), limit.getUpdateDate()), HttpStatus.BAD_REQUEST);
        } else if (limitProperties.getMinValue().compareTo(newValue) > 0) {
            throw new LimitRespException(String.format("Нельзя понизить лимит на указанную сумму (%s). Лимит не может быть меньше %s", sum, limitProperties.getMinValue()), HttpStatus.BAD_REQUEST);
        }

        limit.setMaxValue(newValue);
        limit.setUpdateDate(dateNow);
        limitRepo.save(limit);

        return new LimitRespDto(userId, oldValue, newValue);
    }

}
