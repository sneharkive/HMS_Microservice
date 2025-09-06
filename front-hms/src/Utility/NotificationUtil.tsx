import { notifications } from "@mantine/notifications";
import { IconCheck, IconX } from "@tabler/icons-react";

const SuccessNotification = ( message: string) => {
  notifications.show({
    title: "Success",
    message: message,
    withCloseButton: true,
    icon: <IconCheck style={{ width: "90%", height: "90%" }} />,
    color: "teal",
    withBorder: true,
    className: "!border-teal-500",
  });
}

const ErrorNotification = (message: string) => {
  notifications.show({
    title: "Error",
    message: message,
    withCloseButton: true,
    icon: <IconX style={{ width: "90%", height: "90%" }} />,
    color: "red",
    withBorder: true,
    className: "!border-red-500",
  });
}

export { SuccessNotification, ErrorNotification };