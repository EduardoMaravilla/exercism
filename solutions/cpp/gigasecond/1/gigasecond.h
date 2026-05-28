#pragma once

#include <boost/date_time/posix_time/ptime.hpp>

namespace gigasecond {

    boost::posix_time::ptime advance(boost::posix_time::ptime now);

} // namespace gigasecond