import { ActionIcon, Button } from "@mantine/core";
import ProfileMenu from "./ProfileMenu";
import {
  IconBellRinging,
  IconLayoutSidebarLeftCollapseFilled,
} from "@tabler/icons-react";
import { Link } from "react-router";

const Header = () => {
  return (
    <div className="bg-light shadow-lg w-full h-16 flex justify-between px-6 items-center">
      <ActionIcon variant="transparent" size="lg" aria-label="Settings">
        <IconLayoutSidebarLeftCollapseFilled
          style={{ width: "90%", height: "90%" }}
          stroke={1.5}
        />
      </ActionIcon>
      <div className="flex gap-5 items-center">
        <Link to="/login">
          <Button>Login</Button>
        </Link>
        <ActionIcon variant="transparent" size="md" aria-label="Settings">
          <IconBellRinging
            style={{ width: "90%", height: "90%" }}
            stroke={1.7}
          />
        </ActionIcon>

        <ProfileMenu />
      </div>
    </div>
  );
};

export default Header;
