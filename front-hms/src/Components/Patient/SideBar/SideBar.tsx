import { Avatar, Text } from "@mantine/core";
import {
  IconCalendarCheck,
  IconHeartbeat,
  IconLayoutGrid,
  IconUser,
} from "@tabler/icons-react";
import { useSelector } from "react-redux";
import { NavLink, Link } from "react-router-dom";

const links = [
  {
    name: "Dashboard",
    url: "/patient/dashboard",
    icon: <IconLayoutGrid stroke={1.5} />,
  },
  {
    name: "Profile",
    url: "/patient/profile",
    icon: <IconUser stroke={1.5} />,
  },
  {
    name: "Appointments",
    url: "/patient/appointments",
    icon: <IconCalendarCheck stroke={1.5} />,
  }
];

const SideBar = () => {
  const user = useSelector((state: any) => state.user);

  return (
    <div className="flex">
      <div className="w-64">

      </div>
      <div className="fixed bg-dark text-light w-64 flex flex-col gap-7 items-center h-screen overflow-y-auto">
        <Link to="/" className="fixed bg-dark z-[500] py-3 text-cyan-400 flex gap-1 items-center">
          <IconHeartbeat size={40} stroke={2.5} />
          <span className="font-heading text-3xl font-semibold">Pulse</span>
        </Link>

        <div className="flex flex-col gap-5 mt-20">
          <div className="flex flex-col gap-1 items-center">
            <div className="p-1 bg-white rounded-full shadow-lg">
              <Avatar
                variant="filled"
                src="/avatar.png"
                size="xl"
                alt="My Profile"
              />
            </div>
            <span className="font-medium ">{user.name}</span>
            <Text c="dimmed" size="xs">
              {user.role}
            </Text>
          </div>
          <div className="flex flex-col gap-3">
            {links.map((link) => {
              return (
                <NavLink
                  to={link.url}
                  key={link.url}
                  className={({ isActive }) =>
                    `flex items-center gap-3 w-full font-medium  px-4 py-5 rounded-lg ${
                      isActive
                        ? "bg-cyan-400 text-dark"
                        : "hover:bg-gray-200 hover:text-black"
                    }`
                  }
                >
                  {" "}
                  {link.icon}
                  <span>{link.name}</span>
                </NavLink>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SideBar;
