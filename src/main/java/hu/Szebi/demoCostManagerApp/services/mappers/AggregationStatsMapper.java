package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.services.aggregations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AggregationStatsMapper {

    YearStatsDto toYearStatsDto(YearStatsView view);
    List<YearStatsDto> toYearStatsDtos(List<YearStatsView> views);


    CategoryStatsDto toCategoryStatsDto(CategoryStatsView view);
    List<CategoryStatsDto> toCategoryStatsDtos(List<CategoryStatsView> views);

    ByMonthStatsDto toMonthStatsDto(MonthStatsView view);
    List<ByMonthStatsDto> toMonthStatsDtos(List<MonthStatsView> views);

    ByDayStatsDto toDayStatsDto(DayStatsView view);
    List<ByDayStatsDto> toDayStatsDtos(List<DayStatsView> views);

}
