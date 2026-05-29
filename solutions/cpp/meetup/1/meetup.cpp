#include "meetup.h"

namespace meetup {
    scheduler::scheduler(boost::gregorian::greg_month month, int year) {
        first_of_month_ = boost::gregorian::date(year, month, 1);
        end_of_month_ = first_of_month_.end_of_month();
    }

    boost::gregorian::date scheduler::find_day_in_week(const boost::gregorian::date first_of_month,
                                                        const boost::gregorian::date::day_of_week_type day_of_week) {
        boost::gregorian::date new_date = first_of_month;
        while (new_date.day_of_week() != day_of_week) {
            new_date += boost::gregorian::days(1);
        }
        return new_date;
    }

    boost::gregorian::date scheduler::find_teenth_day(const boost::gregorian::date first_of_month,
                                                       const boost::gregorian::date::day_of_week_type day_of_week) {
        boost::gregorian::date new_date = first_of_month;
        while (new_date.day_of_week() != day_of_week || new_date.day() < 13 || new_date.day() > 19) {
            new_date += boost::gregorian::days(1);
        }
        return new_date;
    }

    boost::gregorian::date scheduler::monteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::tuesteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::wednesteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::thursteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::friteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::saturteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::sunteenth() const {
        return find_teenth_day(first_of_month_, boost::gregorian::Sunday);
    }

    boost::gregorian::date scheduler::first_monday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::first_tuesday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::first_wednesday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::first_thursday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::first_friday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::first_saturday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::first_sunday() const {
        return find_day_in_week(first_of_month_, boost::gregorian::Sunday);
    }

    boost::gregorian::date scheduler::second_monday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::second_tuesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::second_wednesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::second_thursday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::second_friday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::second_saturday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::second_sunday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(7), boost::gregorian::Sunday);
    }

    boost::gregorian::date scheduler::third_monday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::third_tuesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::third_wednesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::third_thursday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::third_friday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::third_saturday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::third_sunday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(14), boost::gregorian::Sunday);
    }

    boost::gregorian::date scheduler::fourth_monday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::fourth_tuesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::fourth_wednesday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::fourth_thursday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::fourth_friday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::fourth_saturday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::fourth_sunday() const {
        return find_day_in_week(first_of_month_ + boost::gregorian::days(21), boost::gregorian::Sunday);
    }

    boost::gregorian::date scheduler::last_monday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Monday);
    }

    boost::gregorian::date scheduler::last_tuesday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Tuesday);
    }

    boost::gregorian::date scheduler::last_wednesday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Wednesday);
    }

    boost::gregorian::date scheduler::last_thursday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Thursday);
    }

    boost::gregorian::date scheduler::last_friday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Friday);
    }

    boost::gregorian::date scheduler::last_saturday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Saturday);
    }

    boost::gregorian::date scheduler::last_sunday() const {
        return find_day_in_week(end_of_month_ - boost::gregorian::days(6), boost::gregorian::Sunday);
    }
}
