#pragma once
#include <boost/date_time/gregorian/gregorian_types.hpp>

namespace meetup {
    class scheduler {
    public:
        scheduler(boost::gregorian::greg_month month, int year);

        [[nodiscard]] boost::gregorian::date monteenth() const;

        [[nodiscard]] boost::gregorian::date tuesteenth() const;

        [[nodiscard]] boost::gregorian::date wednesteenth() const;

        [[nodiscard]] boost::gregorian::date thursteenth() const;

        [[nodiscard]] boost::gregorian::date friteenth() const;

        [[nodiscard]] boost::gregorian::date saturteenth() const;

        [[nodiscard]] boost::gregorian::date sunteenth() const;

        [[nodiscard]] boost::gregorian::date first_monday() const;

        [[nodiscard]] boost::gregorian::date first_tuesday() const;

        [[nodiscard]] boost::gregorian::date first_wednesday() const;

        [[nodiscard]] boost::gregorian::date first_thursday() const;

        [[nodiscard]] boost::gregorian::date first_friday() const;

        [[nodiscard]] boost::gregorian::date first_saturday() const;

        [[nodiscard]] boost::gregorian::date first_sunday() const;

        [[nodiscard]] boost::gregorian::date second_monday() const;

        [[nodiscard]] boost::gregorian::date second_tuesday() const;

        [[nodiscard]] boost::gregorian::date second_wednesday() const;

        [[nodiscard]] boost::gregorian::date second_thursday() const;

        [[nodiscard]] boost::gregorian::date second_friday() const;

        [[nodiscard]] boost::gregorian::date second_saturday() const;

        [[nodiscard]] boost::gregorian::date second_sunday() const;

        [[nodiscard]] boost::gregorian::date third_monday() const;

        [[nodiscard]] boost::gregorian::date third_tuesday() const;

        [[nodiscard]] boost::gregorian::date third_wednesday() const;

        [[nodiscard]] boost::gregorian::date third_thursday() const;

        [[nodiscard]] boost::gregorian::date third_friday() const;

        [[nodiscard]] boost::gregorian::date third_saturday() const;

        [[nodiscard]] boost::gregorian::date third_sunday() const;

        [[nodiscard]] boost::gregorian::date fourth_monday() const;

        [[nodiscard]] boost::gregorian::date fourth_tuesday() const;

        [[nodiscard]] boost::gregorian::date fourth_wednesday() const;

        [[nodiscard]] boost::gregorian::date fourth_thursday() const;

        [[nodiscard]] boost::gregorian::date fourth_friday() const;

        [[nodiscard]] boost::gregorian::date fourth_saturday() const;

        [[nodiscard]] boost::gregorian::date fourth_sunday() const;

        [[nodiscard]] boost::gregorian::date last_monday() const;

        [[nodiscard]] boost::gregorian::date last_tuesday() const;

        [[nodiscard]] boost::gregorian::date last_wednesday() const;

        [[nodiscard]] boost::gregorian::date last_thursday() const;

        [[nodiscard]] boost::gregorian::date last_friday() const;

        [[nodiscard]] boost::gregorian::date last_saturday() const;

        [[nodiscard]] boost::gregorian::date last_sunday() const;

    private:
        boost::gregorian::date first_of_month_{};
        boost::gregorian::date end_of_month_{};

        static boost::gregorian::date find_teenth_day(boost::gregorian::date first_of_month,
                                                      boost::gregorian::date::day_of_week_type day_of_week);

        static boost::gregorian::date find_day_in_week(boost::gregorian::date first_of_month,
                                                       boost::gregorian::date::day_of_week_type day_of_week);
    };
} // namespace meetup
